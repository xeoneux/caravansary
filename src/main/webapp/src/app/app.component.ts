import { Component, OnInit } from "@angular/core";
import { FormControl, FormGroup } from "@angular/forms";
import { Headers, Http, RequestOptions, Response } from "@angular/http";

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
  checkin: string;
  checkout: string;
  roomSearch: FormGroup;
  public submitted: boolean;

  constructor(private http: Http) {}

  ngOnInit() {
    this.roomSearch = new FormGroup({
      checkin: new FormControl(""),
      checkout: new FormControl("")
    });

    const roomSearch$ = this.roomSearch.valueChanges;
    roomSearch$.subscribe((value: RoomSearch) => {
      this.checkin = value.checkin;
      this.checkout = value.checkout;
    });
  }

  onSubmit({ value, valid }: { value: RoomSearch; valid: boolean }) {
    this.getAll().subscribe(
      rooms => (this.rooms = rooms),
      err => console.log(err)
    );
  }

  reserveRoom(value: string) {
    const request = new ReserveRoomRequest(value, this.checkin, this.checkout);
    this.createReservation(request);
  }

  createReservation(body: ReserveRoomRequest) {
    const headers = new Headers({ "Content-Type": "application/json" });
    const options = new RequestOptions({ headers });

    this.http
      .post(this.baseUrl + "/room/reservation/v1", body, options)
      .subscribe(console.log);
  }

  getAll(): Observable<Room[]> {
    return this.http
      .get(
        this.baseUrl +
          "/room/reservation/v1" +
          "?checkin=" +
          this.checkin +
          "&checkout=" +
          this.checkout
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

export class ReserveRoomRequest {
  roomId: string;
  checkin: string;
  checkout: string;

  constructor(roomId: string, checkin: string, checkout: string) {
    this.roomId = roomId;
    this.checkin = checkin;
    this.checkout = checkout;
  }
}
