import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PitComponent } from './pit.component';
import { By } from '@angular/platform-browser';

describe('PitComponent', () => {
  let component: PitComponent;
  let fixture: ComponentFixture<PitComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PitComponent],
    })
    .compileComponents();

    fixture = TestBed.createComponent(PitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should render the correct number of stones', () => {
    component.stones = 5;
    fixture.detectChanges();

    const stonesElements = fixture.debugElement.queryAll(By.css('app-stone'));
    expect(stonesElements.length).toBe(5);
  });

  it('should emit pitSelected when the pit is clicked', () => {
    spyOn(component.pitSelected, 'emit');
    const pitElement = fixture.debugElement.query(By.css('.pit'));
    
    pitElement.triggerEventHandler('click', null);
    
    expect(component.pitSelected.emit).toHaveBeenCalled();
  });

  it('should render the correct number of stones in the text', () => {
    component.stones = 4;
    fixture.detectChanges();
    
    const stoneText = fixture.debugElement.query(By.css('p')).nativeElement.textContent;
    expect(stoneText).toContain('4');
  });

  it('should return correct border class for player 1', () => {
    component.player = 1;
    expect(component.getPitBorder()).toBe('pit-box-1');
  });

  it('should return correct border class for player 2', () => {
    component.player = 2;
    expect(component.getPitBorder()).toBe('pit-box-2');
  });
});
