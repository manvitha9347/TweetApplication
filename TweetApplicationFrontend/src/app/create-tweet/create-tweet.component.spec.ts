import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialogModule, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { of } from 'rxjs';
import { TweetService } from '../service/tweet.service';

import { CreateTweetComponent } from './create-tweet.component';

describe('CreateTweetComponent', () => {
  let component: CreateTweetComponent;
  let fixture: ComponentFixture<CreateTweetComponent>;
  const matService = jasmine.createSpyObj(MatDialogRef,['close'])
 // const tweetService = jasmine.createSpyObj(TweetService,['postTweet','updateTweet'])
let dialog:any;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateTweetComponent ],
      imports:[HttpClientTestingModule,MatDialogModule],
      providers:[
        {provide : MatDialogRef, useValue : {}},
        {
          provide : MAT_DIALOG_DATA,
          useValue : {}
        }
      ]
    })
    .compileComponents();
    dialog = TestBed.get(MatDialogRef);
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateTweetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('call,handel submit',()=>{
    const spyObj = spyOn(component,'handelSubmit').and.callThrough();
    //const dialogSpy = spyOn(dialog.'close');
    component.handelSubmit();
    expect(spyObj).toHaveBeenCalled();
  })
  it('shouould call updateTweet',()=>{
    const spyObj = spyOn(component,'updateTweet').and.callThrough();
    component.updateTweet();
    expect(spyObj).toHaveBeenCalled();
  })

});
