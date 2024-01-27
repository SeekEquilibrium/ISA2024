import { mergeApplicationConfig, ApplicationConfig } from '@angular/core';
import { provideServerRendering } from '@angular/platform-server';
import { appConfig } from './app.config';
import { AuthService } from './services/AuthService';

const serverConfig: ApplicationConfig = {
  providers: [
    provideServerRendering()
  ]
  
};

export const config = mergeApplicationConfig(appConfig, serverConfig);
