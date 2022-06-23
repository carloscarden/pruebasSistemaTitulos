import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IAlumnoAnalitico, AlumnoAnalitico } from '../alumno-analitico.model';
import { AlumnoAnaliticoService } from '../service/alumno-analitico.service';

import { AlumnoAnaliticoRoutingResolveService } from './alumno-analitico-routing-resolve.service';

describe('AlumnoAnalitico routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: AlumnoAnaliticoRoutingResolveService;
  let service: AlumnoAnaliticoService;
  let resultAlumnoAnalitico: IAlumnoAnalitico | undefined;

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
    routingResolveService = TestBed.inject(AlumnoAnaliticoRoutingResolveService);
    service = TestBed.inject(AlumnoAnaliticoService);
    resultAlumnoAnalitico = undefined;
  });

  describe('resolve', () => {
    it('should return IAlumnoAnalitico returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultAlumnoAnalitico = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultAlumnoAnalitico).toEqual({ id: 123 });
    });

    it('should return new IAlumnoAnalitico if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultAlumnoAnalitico = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultAlumnoAnalitico).toEqual(new AlumnoAnalitico());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as AlumnoAnalitico })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultAlumnoAnalitico = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultAlumnoAnalitico).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
