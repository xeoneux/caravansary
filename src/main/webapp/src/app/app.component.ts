import { Component, OnInit } from "@angular/core";
import { FormControl, FormGroup } from "@angular/forms";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"]
})
export class AppComponent implements OnInit {
  roomSearch: FormGroup;
  public submitted: boolean;

  ngOnInit() {
    this.roomSearch = new FormGroup({
      checkin: new FormControl(""),
      checkout: new FormControl("")
    });
  }

  onSubmit({ value, valid }: { value: RoomSearch; valid: boolean }) {
    console.log(value);
  }
}

export interface RoomSearch {
  checkin: string;
  checkout: string;
}
