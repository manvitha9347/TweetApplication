import { HttpClient } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { of } from 'rxjs';

import { TweetActionService } from './tweet-action.service';

describe('TweetActionService', () => {
  let service: TweetActionService;
  const http = jasmine.createSpyObj(HttpClient, ['post', 'get']);
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    service = new TweetActionService(http);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
  it('should call getLikes', () => {
    http.post.and.returnValue(of(10));
    service.getLikes('sandeep', 1).subscribe({
      next: (data) => expect(data).toEqual(10),
    });
  });
  it('should call getNumberOfLikes', () => {
    http.get.and.returnValue(of(10));
    service.getNumberOfLikes(1).subscribe({
      next: (data) => expect(data).toEqual(10),
    });
  });
  it('should call getComments', () => {
    const mockComment = {
      comment: 'hai all',
    };
    http.post.and.returnValue(of(mockComment));
    service.getComments('sandeep', 1, mockComment).subscribe({
      next: (data) => expect(data).toEqual(mockComment),
    });
  });
  it('should call getCommentByTweetId', () => {
    const mockComment = {
      comment: 'hai all',
    };
    const mockResponse: any[] = [{ comment: 'Hai all' }];
    http.get.and.returnValue(of(mockResponse));
    service.getCommentByTweetId(1).subscribe({
      next: (data) => expect(data).toEqual(mockResponse),
    });
  });

});
