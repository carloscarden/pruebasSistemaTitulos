import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { INroSerieTitulo, NroSerieTitulo } from '../nro-serie-titulo.model';
import { NroSerieTituloService } from '../service/nro-serie-titulo.service';

import { NroSerieTituloRoutingResolveService } from './nro-serie-titulo-routing-resolve.service';

describe('NroSerieTitulo routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: NroSerieTituloRoutingResolveService;
  let service: NroSerieTituloService;
  let resultNroSerieTitulo: INroSerieTitulo | undefined;

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
    routingResolveService = TestBed.inject(NroSerieTituloRoutingResolveService);
    service = TestBed.inject(NroSerieTituloService);
    resultNroSerieTitulo = undefined;
  });

  describe('resolve', () => {
    it('should return INroSerieTitulo returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultNroSerieTitulo = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultNroSerieTitulo).toEqual({ id: 123 });
    });

    it('should return new INroSerieTitulo if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultNroSerieTitulo = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultNroSerieTitulo).toEqual(new NroSerieTitulo());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as NroSerieTitulo })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultNroSerieTitulo = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultNroSerieTitulo).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
