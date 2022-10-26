import { Component, OnInit } from '@angular/core';
import { TweetService } from '../service/tweet.service';

@Component({
  selector: 'app-tweet',
  templateUrl: './tweet.component.html',
  styleUrls: ['./tweet.component.css'],
})
export class TweetComponent implements OnInit {
  loaded = false;
  constructor(private tweetService: TweetService) {}
  ngOnInit(): void {
    setTimeout(() => (this.loaded = true), 500);
  }
}
