import { ComponentFixture, TestBed } from '@angular/core/testing';
import { StoneComponent } from './stone.component';
import { By } from '@angular/platform-browser';

describe('StoneComponent', () => {
  let component: StoneComponent;
  let fixture: ComponentFixture<StoneComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StoneComponent],
    })
    .compileComponents();

    fixture = TestBed.createComponent(StoneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should apply the correct class for player 1', () => {
    component.player = 1;
    fixture.detectChanges();

    const stoneDiv = fixture.debugElement.query(By.css('div')).nativeElement;
    expect(stoneDiv.classList).toContain('player-1');
  });

  it('should apply the correct class for player 2', () => {
    component.player = 2;
    fixture.detectChanges();

    const stoneDiv = fixture.debugElement.query(By.css('div')).nativeElement;
    expect(stoneDiv.classList).toContain('player-2');
  });

  it('should return "player-1" when player is 1', () => {
    component.player = 1;
    expect(component.getStoneColor()).toBe('player-1');
  });

  it('should return "player-2" when player is 2', () => {
    component.player = 2;
    expect(component.getStoneColor()).toBe('player-2');
  });
});
