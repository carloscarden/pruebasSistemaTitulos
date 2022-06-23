import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IOfertaEducativa, OfertaEducativa } from '../oferta-educativa.model';
import { OfertaEducativaService } from '../service/oferta-educativa.service';

import { OfertaEducativaRoutingResolveService } from './oferta-educativa-routing-resolve.service';

describe('OfertaEducativa routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: OfertaEducativaRoutingResolveService;
  let service: OfertaEducativaService;
  let resultOfertaEducativa: IOfertaEducativa | undefined;

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
    routingResolveService = TestBed.inject(OfertaEducativaRoutingResolveService);
    service = TestBed.inject(OfertaEducativaService);
    resultOfertaEducativa = undefined;
  });

  describe('resolve', () => {
    it('should return IOfertaEducativa returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultOfertaEducativa = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultOfertaEducativa).toEqual({ id: 123 });
    });

    it('should return new IOfertaEducativa if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultOfertaEducativa = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultOfertaEducativa).toEqual(new OfertaEducativa());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as OfertaEducativa })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultOfertaEducativa = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultOfertaEducativa).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
