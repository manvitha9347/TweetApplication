import { HttpErrorResponse } from '@angular/common/http';
import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { CreateTweetComponent } from '../create-tweet/create-tweet.component';
import { TweetResponse } from '../models/tweet.modal';
import { TweetComment } from '../models/TweetComment.modal';
import { TweetActionService } from '../service/tweet-action.service';
import { TweetService } from '../service/tweet.service';
import { TweetComponent } from '../tweet/tweet.component';

@Component({
  selector: 'app-view-tweets',
  templateUrl: './view-tweets.component.html',
  styleUrls: ['./view-tweets.component.css'],
})
export class ViewTweetsComponent implements OnInit {
  constructor(
    private tweetService: TweetService,
    public dialog: MatDialog,
    private router: Router,
    private tweetAction: TweetActionService
  ) {}
  tweets: TweetResponse[] = [];
  errorMessage: string = '';
  ngOnInit(): void {
    if (this.router.url === '/tweets') {
      this.getTweetByUserName();
    } else {
      this.refresh();
    }
  }

  openDialog() {
    const dialogRef = this.dialog
      .open(CreateTweetComponent, {
        width: '30%',
      })
      .afterClosed()
      .subscribe((val) => {
        this.refresh();
      });
  }

  deleteTweet(tweetId: number) {
    let userName = sessionStorage.getItem('userId');
    if (userName) {
      this.tweetService.deleteTweetById(userName, tweetId).subscribe(
        () => {
          this.refresh();
        },
        (err) => {
          this.refresh();
        }
      );
    }
  }

  editData(tweet: TweetResponse) {
    this.dialog
      .open(CreateTweetComponent, {
        data: tweet,
        width: '30%',
      })
      .afterClosed()
      .subscribe(() => {
        this.refresh();
      });
  }

  refresh() {
    this.tweetService.getAllTweets().subscribe(
      (data: TweetResponse[]) => {
        this.tweets = data;
      },
      (err: HttpErrorResponse) => {
        this.tweets = [];
        this.errorMessage = err.error;
      }
    );
  }
  get username() {
    return sessionStorage.getItem('userId');
  }

  getTweetByUserName() {
    let userName = sessionStorage.getItem('userId');
    if (userName) {
      this.tweetService
        .getTweetByUsername(userName)
        .subscribe((data: TweetResponse[]) => {
          this.tweets = data;
        });
    }
  }

  getLikes(tweetId: number) {
    let userName = sessionStorage.getItem('userId');
    if (userName) {
      this.tweetAction.getLikes(userName, tweetId).subscribe((data) => {
        this.refresh();
      });
    }
  }

  getCommets(comment: TweetComment, tweetId: number) {
    let userName = sessionStorage.getItem('userId');
    if (userName) {
      this.tweetAction.getComments(userName, tweetId, comment).subscribe(() => {
        this.refresh();
      });
    }
  }
}
