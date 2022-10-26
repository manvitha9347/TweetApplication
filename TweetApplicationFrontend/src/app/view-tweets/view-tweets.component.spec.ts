import { HttpClientTestingModule } from '@angular/common/http/testing';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { RouterTestingModule } from '@angular/router/testing';
import { TweetService } from '../service/tweet.service';

import { ViewTweetsComponent } from './view-tweets.component';

describe('ViewTweetsComponent', () => {
  let component: ViewTweetsComponent;
  let fixture: ComponentFixture<ViewTweetsComponent>;
  let tweetService = jasmine.createSpyObj(TweetService, ['getAllTweets']);
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ViewTweetsComponent],
      imports: [
        HttpClientTestingModule,
        MatDialogModule,
        RouterTestingModule.withRoutes([]),
      ],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewTweetsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('call refresh', async(() => {
    const spyObj = spyOn(component, 'refresh').and.callThrough();
    component.refresh();
    expect(spyObj).toHaveBeenCalled();
  }));
 it('should call deleteTweet',()=>{
   const spyObj = spyOn(component,'deleteTweet');
   component.deleteTweet(1);
   expect(spyObj).toHaveBeenCalled();
 })
});
