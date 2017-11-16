import { Component, OnInit } from "@angular/core";
import { FormControl, FormGroup } from "@angular/forms";
import { Http, Response } from "@angular/http";

import { Observable } from "rxjs/Rx";

import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"]
})
export class AppComponent implements OnInit {
  private baseUrl = "http://localhost:8080";

  rooms: Room[];
  roomSearch: FormGroup;
  public submitted: boolean;

  constructor(private http: Http) {}

  ngOnInit() {
    this.roomSearch = new FormGroup({
      checkin: new FormControl(""),
      checkout: new FormControl("")
    });
  }

  onSubmit({ value, valid }: { value: RoomSearch; valid: boolean }) {
    this.getAll(value).subscribe(
      rooms => (this.rooms = rooms),
      err => console.log(err)
    );
  }

  reserveRoom(value: string) {
    console.log("Room id for reservation: " + value);
  }

  getAll(value: RoomSearch): Observable<Room[]> {
    return this.http
      .get(
        this.baseUrl +
          "/room/reservation/v1" +
          "?checkin=" +
          value.checkin +
          "&checkout=" +
          value.checkout
      )
      .map(this.mapRoom);
  }

  mapRoom(response: Response): Room[] {
    return response.json().content;
  }
}

export interface Room {
  id: string;
  roomNumber: string;
  price: string;
  links: string;
}

export interface RoomSearch {
  checkin: string;
  checkout: string;
}
