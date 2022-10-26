import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { commentResponse } from '../models/CommentResponse.modal';
import { TweetComment } from '../models/TweetComment.modal';
import { TweetActionService } from '../service/tweet-action.service';

@Component({
  selector: 'app-tweet-comments',
  templateUrl: './tweet-comments.component.html',
  styleUrls: ['./tweet-comments.component.css']
})
export class TweetCommentsComponent implements OnInit, OnChanges {

  @Input() tweetId: number = 0;
  @Output() comment = new EventEmitter();
  showForm = false;
  commentResponse:any
  showComments = false;
  numberOfComments:number=0;
  constructor(private tweetAction: TweetActionService) { }

  ngOnChanges(changes: SimpleChanges): void {
    this.tweetAction.getCommentByTweetId(changes['tweetId'].currentValue).subscribe((data:commentResponse[])=>{
      this.commentResponse = data;
      this.numberOfComments=data?.length;
      this.showComments = true;
    });
  }

  commentForm:FormGroup = new FormGroup({
    comment : new FormControl(null,Validators.required)
  })
  ngOnInit(): void {
  }

  commentCLicked(){
    this.showForm=true;
  }

  handelComment(action:string){
    if(action === 'cancel'){
      this.showForm=false;
    }else{
      this.showForm=false;
      this.comment.emit(this.commentForm.value);
    }
  }

}
