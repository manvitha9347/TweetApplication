import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { TweetResponse } from '../models/tweet.modal';

@Injectable({
  providedIn: 'root',
})
export class TweetService {
  constructor(private http: HttpClient) {}

  ROOT_URL:string ='http://tweetservice-env.eba-hzbqjnu5.ap-northeast-1.elasticbeanstalk.com';
  getAllTweets() {
    return this.http
      .get<TweetResponse[]>(this.ROOT_URL+'/api/v1.0/tweets/all')
      .pipe(map((data) => data.reverse()));
  }
  postTweet(tweet: any, userName: string) {
    return this.http.post(
      this.ROOT_URL+`/api/v1.0/tweets/${userName}/add`,
      tweet
    );
  }
  getTweetByUsername(userName: string) {
    return this.http.get<TweetResponse[]>(
      this.ROOT_URL+`/api/v1.0/tweets/${userName}`
    );
  }
  getAllUsers(): Observable<string[]> {
    return this.http.get<string[]>(
      'http://loginservice-env.eba-aain2mmm.ap-northeast-1.elasticbeanstalk.com/api/v1.0/tweets/users'
    );
  }
  deleteTweetById(userName: string, tweetId: number): Observable<string> {
    return this.http.delete<string>(
      this.ROOT_URL+`/api/v1.0/tweets/${userName}/delete/${tweetId}`,
      { responseType: 'string' as 'json' }
    );
  }
  updateTweet(
    userName: string,
    tweetId: number,
    tweet: any
  ): Observable<TweetResponse> {
    return this.http.put<TweetResponse>(
      this.ROOT_URL+`/api/v1.0/tweets/${userName}/update/${tweetId}`,
      tweet
    );
  }
}
