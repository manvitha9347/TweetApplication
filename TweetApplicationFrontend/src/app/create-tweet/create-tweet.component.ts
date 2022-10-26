import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TweetResponse } from '../models/tweet.modal';
import { TweetService } from '../service/tweet.service';

@Component({
  selector: 'app-create-tweet',
  templateUrl: './create-tweet.component.html',
  styleUrls: ['./create-tweet.component.css']
})
export class CreateTweetComponent implements OnInit {
  actionButton: string = '';
  title: string = '';
  constructor(
    private tweetService: TweetService,
    private dialogRef: MatDialogRef<CreateTweetComponent>,
    @Inject(MAT_DIALOG_DATA) private editData: TweetResponse) { }

  ngOnInit(): void {
    if (this.editData) {
      this.actionButton = 'Update';
      this.title = 'Update Tweet'
      this.createTweet.get('tweet')?.setValue(this.editData.tweet);
      this.createTweet.get('tweetPostTime')?.setValue(this.editData.tweetPostTime);
      this.createTweet.get('tag')?.setValue(this.editData.tag);
    } else {
      this.actionButton = 'Post';
      this.title = 'Post Tweet';
    }
  }

  createTweet: FormGroup = new FormGroup({
    tweet: new FormControl(null, [Validators.required]),
    tweetPostTime: new FormControl(new Date().toLocaleDateString("en-US", {
      hour: '2-digit',
      minute: '2-digit',
      second: '2-digit',
      hour12: true
    }), [Validators.required]),
    tag: new FormControl(null, [Validators.required])
  })
  handelSubmit() {
    if (!this.editData) {
      let userName = sessionStorage.getItem('userId');
      if (userName) {
        this.tweetService.postTweet(this.createTweet.value, userName).subscribe((data) => {
          this.dialogRef.close()
        });
      }
    } else {

      this.updateTweet();
    }
  }
  updateTweet() {
    let userName = sessionStorage.getItem('userId');
    if (userName) {
      this.tweetService.updateTweet(userName, this.editData.tweetId, this.createTweet.value).subscribe((data) => {
        this.dialogRef.close()
      });
    }
  }
}
