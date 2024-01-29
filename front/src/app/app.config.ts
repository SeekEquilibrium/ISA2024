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
import { TokenInterceptor } from './tools/TokenInterceptor';
import { RoleGuard } from './tools/Role-guard';
import { AuthGuard } from './tools/Auth-guard';

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideClientHydration(
      withHttpTransferCacheOptions({ includePostRequests: true })
    ),
    AuthGuard,
    RoleGuard,
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
