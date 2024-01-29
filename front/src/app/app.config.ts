import { ApplicationConfig, importProvidersFrom } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import {
  provideClientHydration,
  withHttpTransferCacheOptions,
} from '@angular/platform-browser';
import { provideAnimations } from '@angular/platform-browser/animations';
import {
  HTTP_INTERCEPTORS,
  HttpClientModule,
  provideHttpClient,
  withFetch,
  withInterceptorsFromDi,
} from '@angular/common/http';
import { AuthGuard } from './tools/Auth-guard';
import { TokenInterceptor } from './tools/TokenInterceptor';

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideClientHydration(
      withHttpTransferCacheOptions({ includePostRequests: true })
    ),
    provideAnimations(),
    importProvidersFrom(HttpClientModule),
    provideHttpClient(withFetch(),withInterceptorsFromDi()),
    {
      provide:HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi:true,
    },
    
  ],
};
