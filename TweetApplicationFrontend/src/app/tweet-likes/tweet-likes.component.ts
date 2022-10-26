import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { Observable } from 'rxjs';
import { TweetActionService } from '../service/tweet-action.service';

@Component({
  selector: 'app-tweet-likes',
  templateUrl: './tweet-likes.component.html',
  styleUrls: ['./tweet-likes.component.css']
})
export class TweetLikesComponent implements OnInit, OnChanges {
  constructor(private tweetAction: TweetActionService) { }
  @Input() tweetId:number =0;
  @Output() islikesClicked = new EventEmitter();
  numberOfLikes:number | undefined
  ngOnInit(): void {
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.tweetAction.getNumberOfLikes(changes['tweetId'].currentValue).subscribe(
      (data) => this.numberOfLikes = data
    )

  }
  likesClicked() {
    this.islikesClicked.emit('');
  }
}
