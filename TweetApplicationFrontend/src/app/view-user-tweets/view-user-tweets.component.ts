import { Component, OnInit } from '@angular/core';
import { TweetResponse } from '../models/tweet.modal';
import { TweetService } from '../service/tweet.service';

@Component({
  selector: 'app-view-user-tweets',
  templateUrl: './view-user-tweets.component.html',
  styleUrls: ['./view-user-tweets.component.css'],
})
export class ViewUserTweetsComponent implements OnInit {
  constructor(private tweetService: TweetService) {}
  tweets: any;
  ngOnInit(): void {
    let userName = sessionStorage.getItem('userId');
    if (userName) {
      this.tweetService
        .getTweetByUsername(userName)
        .subscribe((data: TweetResponse[]) => {
          this.tweets = data;
        });
    }
  }
}
