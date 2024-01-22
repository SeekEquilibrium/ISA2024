import { CommonModule } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';

interface carouselImage {
  imageSrc: string;
  imageAlt: string;
}
 var savedInterval: ReturnType<typeof setInterval>;

@Component({
  selector: 'app-carosel',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './carosel.component.html',
  styleUrl: './carosel.component.css',
})
export class CaroselComponent implements OnInit {
  @Input() images: carouselImage[] = [];
  @Input() indicators = true;
  @Input() autoSlide = false;

  selectedIndex = 0;

  ngOnInit(): void {
    
    if (this.autoSlide) {
      this.autoSlideImages();
    }
    
  }
  ngOnDestroy() {
    if (savedInterval) {
      clearInterval(savedInterval);
    }
  }

  autoSlideImages(): void {
    
    savedInterval = setInterval(() =>{
      this.nextImage()
    }
    ,3000);
    
  }

  nextImage(): void {
    
    if (this.selectedIndex === this.images.length - 1) {
      this.selectedIndex = 0;
    } else {
      this.selectedIndex++;
    }
    
  }

  selectImage(index: number): void {
    this.selectedIndex = index;
  }
}
