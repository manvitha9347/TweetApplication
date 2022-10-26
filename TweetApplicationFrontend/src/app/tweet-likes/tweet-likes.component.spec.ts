import { HttpClientTestingModule } from '@angular/common/http/testing';
import { Component, SimpleChange } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TweetLikesComponent } from './tweet-likes.component';

describe('TweetLikesComponent', () => {
  let component: TweetLikesComponent;
  let fixture: ComponentFixture<TweetLikesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TweetLikesComponent ],
      imports:[HttpClientTestingModule]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TweetLikesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
