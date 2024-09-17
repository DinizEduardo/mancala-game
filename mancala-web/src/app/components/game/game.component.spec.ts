import { ComponentFixture, TestBed } from '@angular/core/testing';
import { GameComponent } from './game.component';
import { GameService } from '../../services/game.service';
import { By } from '@angular/platform-browser';
import { provideHttpClientTesting } from '@angular/common/http/testing';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { importProvidersFrom } from '@angular/core';
import { provideHttpClient } from '@angular/common/http';

describe('GameComponent', () => {
  let component: GameComponent;
  let fixture: ComponentFixture<GameComponent>;
  let gameService: GameService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        GameComponent
      ], 
      providers: [
        provideHttpClient(),
        provideHttpClientTesting(), 
        GameService,
        importProvidersFrom(MatSnackBarModule)
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GameComponent);
    component = fixture.componentInstance;
    gameService = TestBed.inject(GameService);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should render the title "Welcome to Mancala Game"', () => {
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.querySelector('h1')?.textContent).toContain('Welcome to Mancala Game');
  });

  it('should call newGame when the button is clicked', () => {
    spyOn(component, 'newGame'); 
    const button = fixture.debugElement.query(By.css('button')).nativeElement;
    button.click();
    fixture.detectChanges();
    expect(component.newGame).toHaveBeenCalled();
  });

  it('should contain app-board component', () => {
    const boardElement = fixture.debugElement.query(By.css('app-board'));
    expect(boardElement).toBeTruthy();
  });
});
