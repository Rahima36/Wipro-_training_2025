import { ApplicationConfig, provideBrowserGlobalErrorListeners, provideZonelessChangeDetection } from '@angular/core';
import { provideClientHydration, withEventReplay } from '@angular/platform-browser';
import { provideRouter } from '@angular/router'; // <-- ADD THIS LINE
import { routes } from './app.routes'; // <-- ADD THIS LINE

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideZonelessChangeDetection(),
    provideRouter(routes), // <-- ADD THIS LINE
    provideClientHydration(withEventReplay())
  ]
};