import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IAlumnoEstabOferta, AlumnoEstabOferta } from '../alumno-estab-oferta.model';
import { AlumnoEstabOfertaService } from '../service/alumno-estab-oferta.service';

import { AlumnoEstabOfertaRoutingResolveService } from './alumno-estab-oferta-routing-resolve.service';

describe('AlumnoEstabOferta routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: AlumnoEstabOfertaRoutingResolveService;
  let service: AlumnoEstabOfertaService;
  let resultAlumnoEstabOferta: IAlumnoEstabOferta | undefined;

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
    routingResolveService = TestBed.inject(AlumnoEstabOfertaRoutingResolveService);
    service = TestBed.inject(AlumnoEstabOfertaService);
    resultAlumnoEstabOferta = undefined;
  });

  describe('resolve', () => {
    it('should return IAlumnoEstabOferta returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultAlumnoEstabOferta = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultAlumnoEstabOferta).toEqual({ id: 123 });
    });

    it('should return new IAlumnoEstabOferta if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultAlumnoEstabOferta = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultAlumnoEstabOferta).toEqual(new AlumnoEstabOferta());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as AlumnoEstabOferta })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultAlumnoEstabOferta = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultAlumnoEstabOferta).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
