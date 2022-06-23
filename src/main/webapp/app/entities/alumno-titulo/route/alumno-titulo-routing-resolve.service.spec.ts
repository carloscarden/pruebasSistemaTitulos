import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IAlumnoTitulo, AlumnoTitulo } from '../alumno-titulo.model';
import { AlumnoTituloService } from '../service/alumno-titulo.service';

import { AlumnoTituloRoutingResolveService } from './alumno-titulo-routing-resolve.service';

describe('AlumnoTitulo routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: AlumnoTituloRoutingResolveService;
  let service: AlumnoTituloService;
  let resultAlumnoTitulo: IAlumnoTitulo | undefined;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: convertToParamMap({}),
            },
          },
        },
      ],
    });
    mockRouter = TestBed.inject(Router);
    jest.spyOn(mockRouter, 'navigate').mockImplementation(() => Promise.resolve(true));
    mockActivatedRouteSnapshot = TestBed.inject(ActivatedRoute).snapshot;
    routingResolveService = TestBed.inject(AlumnoTituloRoutingResolveService);
    service = TestBed.inject(AlumnoTituloService);
    resultAlumnoTitulo = undefined;
  });

  describe('resolve', () => {
    it('should return IAlumnoTitulo returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultAlumnoTitulo = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultAlumnoTitulo).toEqual({ id: 123 });
    });

    it('should return new IAlumnoTitulo if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultAlumnoTitulo = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultAlumnoTitulo).toEqual(new AlumnoTitulo());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as AlumnoTitulo })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultAlumnoTitulo = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultAlumnoTitulo).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
