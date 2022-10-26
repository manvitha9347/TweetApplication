import { Component, OnInit } from '@angular/core';
import { TweetService } from '../service/tweet.service';

@Component({
  selector: 'app-view-all-users',
  templateUrl: './view-all-users.component.html',
  styleUrls: ['./view-all-users.component.css']
})
export class ViewAllUsersComponent implements OnInit {
  allUsers:string[] =[];
  link:string =''
  constructor(private tweetService:TweetService) { }

  ngOnInit(): void {
    this.tweetService.getAllUsers().subscribe((data:string[])=>{
      this.allUsers=data;
    });
  }

}
