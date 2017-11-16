import { Component, OnInit } from "@angular/core";
import { FormControl, FormGroup } from "@angular/forms";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"]
})
export class AppComponent implements OnInit {
  rooms: Room[];
  roomSearch: FormGroup;
  public submitted: boolean;

  ngOnInit() {
    this.rooms = ROOMS;
    this.roomSearch = new FormGroup({
      checkin: new FormControl(""),
      checkout: new FormControl("")
    });
  }

  onSubmit({ value, valid }: { value: RoomSearch; valid: boolean }) {
    console.log(value);
  }

  reserveRoom(value: string) {
    console.log("Room id for reservation: " + value);
  }
}

const ROOMS: Room[] = [
  {
    id: "2459348",
    roomNumber: "406",
    price: "20",
    links: ""
  },
  {
    id: "2453523",
    roomNumber: "407",
    price: "25",
    links: ""
  },
  {
    id: "2159345",
    roomNumber: "408",
    price: "28",
    links: ""
  }
];

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
