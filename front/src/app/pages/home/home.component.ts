import { Component } from '@angular/core';
import {MatGridListModule} from '@angular/material/grid-list';
import { CaroselComponent } from '../../components/carosel/carosel.component';
import {MatDividerModule} from '@angular/material/divider';
import {MatButtonModule} from '@angular/material/button';


@Component({
  selector: 'app-home',
  standalone: true,
  imports: [MatGridListModule ,CaroselComponent,MatDividerModule, MatButtonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  images = [
    {imageSrc:'assets/img/microscope.jpg',
      imageAlt:'slika'
    },
    {imageSrc:'assets/img/magnet.jpg',
      imageAlt:'slika'
    },
    {imageSrc:'assets/img/dentistchair.jpg',
      imageAlt:'slika'
    },
    {imageSrc:'assets/img/bloodsugar.jpg',
      imageAlt:'slika'
    },
  ]
}
