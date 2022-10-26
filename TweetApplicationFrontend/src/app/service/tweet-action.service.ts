import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { commentResponse } from '../models/CommentResponse.modal';
import { TweetComment } from '../models/TweetComment.modal';

@Injectable({
  providedIn: 'root',
})
export class TweetActionService {
  constructor(private http: HttpClient) {}
  ROOT_URL: string =
    'http://tweetactionservice-env.eba-tgchmm2x.ap-northeast-1.elasticbeanstalk.com';
  getLikes(userName: string, tweetId: number): Observable<number> {
    return this.http.post<number>(
      this.ROOT_URL + `/api/v1.0/tweets/${userName}/likes/${tweetId}`,
      {},
      { responseType: 'number' as 'json' }
    );
  }

  getNumberOfLikes(tweetId: number): Observable<number> {
    return this.http.get<number>(
      this.ROOT_URL + `/api/v1.0/tweets/likes/${tweetId}`
    );
  }

  getComments(
    userName: string,
    tweetId: number,
    comment: TweetComment
  ): Observable<TweetComment> {
    return this.http.post<TweetComment>(
      this.ROOT_URL + `/api/v1.0/tweets/${userName}/comment/${tweetId}`,
      comment
    );
  }
  getCommentByTweetId(tweetId: number): Observable<commentResponse[]> {
    return this.http
      .get<commentResponse[]>(
        this.ROOT_URL + `/api/v1.0/tweets/comment/${tweetId}`
      )
      .pipe(tap((data) => data?.reverse()));
  }
}
