import { HttpClient } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { of } from 'rxjs';

import { TweetService } from './tweet.service';

describe('TweetService', () => {
  let service: TweetService;
  const http = jasmine.createSpyObj(HttpClient, [
    'post',
    'get',
    'put',
    'delete',
  ]);
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    service = new TweetService(http);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
  it('should call getAllTweets', () => {
    let mockTweetResponse:any[] = [{
      tweetId: 1,
      tweet: 'new tweet',
      tweetPostTime: '19-11-2022',
      userName: 'yanda1314',
      tag: '#10',
    }]
    http.get.and.returnValue(of(mockTweetResponse));
    service.getAllTweets().subscribe({
      next:(data)=>expect(data).toEqual(mockTweetResponse)
    })
  });
  it('should call postTweet',()=>{
    let mockTweet = {
      tweet:'hai',
      tag:'#new tweet'
    }
    http.post.and.returnValue(of({tweet:'abcd'}));
    service.postTweet(mockTweet,'sandeep').subscribe({
      next:(data)=>expect(data).toEqual({tweet:'abcd'})
    })
  })
  it('should call getTweetByUsername',()=>{
    let mockTweet:any[] = [{
      tweet:'hai',
    }]
    http.get.and.returnValue(of(mockTweet));
    service.getTweetByUsername('sandeep').subscribe({
      next:(data)=>expect(data).toEqual(mockTweet)
    })
  })
  it('should call getAllUsers',()=>{
    http.get.and.returnValue(of(['sandeep','pandu']));
    service.getAllUsers().subscribe({
      next:(data)=>expect(data).toEqual(['sandeep','pandu'])
    })
  })
  it('should call deleteTweetById',()=>{
    http.delete.and.returnValue(of('post deleted'));
    service.deleteTweetById('sandeep',1).subscribe({
      next:(data)=>expect(data).toEqual('post deleted')
    })
  })
  it('should call updateTweet',()=>{
    let mockTweet = {
      tweet:'hai',
      tag:'#new tweet'
    }
    let mockTweetResponse:any = {
      tweet:'Hello everyone',
      tag:'#new tweet'
    }
    http.put.and.returnValue(of(mockTweetResponse));
    service.updateTweet('sandeep',1,mockTweet).subscribe({
      next:(data)=>expect(data).toEqual(mockTweetResponse)
    })
  })

});
