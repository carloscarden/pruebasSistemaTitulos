import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'alumno',
        data: { pageTitle: 'sistTituApp.alumno.home.title' },
        loadChildren: () => import('./alumno/alumno.module').then(m => m.AlumnoModule),
      },
      {
        path: 'alumno-estab-oferta',
        data: { pageTitle: 'sistTituApp.alumnoEstabOferta.home.title' },
        loadChildren: () => import('./alumno-estab-oferta/alumno-estab-oferta.module').then(m => m.AlumnoEstabOfertaModule),
      },
      {
        path: 'alumno-analitico',
        data: { pageTitle: 'sistTituApp.alumnoAnalitico.home.title' },
        loadChildren: () => import('./alumno-analitico/alumno-analitico.module').then(m => m.AlumnoAnaliticoModule),
      },
      {
        path: 'alumno-titulo',
        data: { pageTitle: 'sistTituApp.alumnoTitulo.home.title' },
        loadChildren: () => import('./alumno-titulo/alumno-titulo.module').then(m => m.AlumnoTituloModule),
      },
      {
        path: 'oferta-educativa',
        data: { pageTitle: 'sistTituApp.ofertaEducativa.home.title' },
        loadChildren: () => import('./oferta-educativa/oferta-educativa.module').then(m => m.OfertaEducativaModule),
      },
      {
        path: 'materia',
        data: { pageTitle: 'sistTituApp.materia.home.title' },
        loadChildren: () => import('./materia/materia.module').then(m => m.MateriaModule),
      },
      {
        path: 'nro-serie-titulo',
        data: { pageTitle: 'sistTituApp.nroSerieTitulo.home.title' },
        loadChildren: () => import('./nro-serie-titulo/nro-serie-titulo.module').then(m => m.NroSerieTituloModule),
      },
      {
        path: 'rendicion',
        data: { pageTitle: 'sistTituApp.rendicion.home.title' },
        loadChildren: () => import('./rendicion/rendicion.module').then(m => m.RendicionModule),
      },
      {
        path: 'turno',
        data: { pageTitle: 'sistTituApp.turno.home.title' },
        loadChildren: () => import('./turno/turno.module').then(m => m.TurnoModule),
      },
      {
        path: 'jornada',
        data: { pageTitle: 'sistTituApp.jornada.home.title' },
        loadChildren: () => import('./jornada/jornada.module').then(m => m.JornadaModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
