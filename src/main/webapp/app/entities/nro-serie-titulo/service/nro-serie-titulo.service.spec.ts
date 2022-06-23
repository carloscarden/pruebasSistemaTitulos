import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import dayjs from 'dayjs/esm';

import { DATE_FORMAT } from 'app/config/input.constants';
import { INroSerieTitulo, NroSerieTitulo } from '../nro-serie-titulo.model';

import { NroSerieTituloService } from './nro-serie-titulo.service';

describe('NroSerieTitulo Service', () => {
  let service: NroSerieTituloService;
  let httpMock: HttpTestingController;
  let elemDefault: INroSerieTitulo;
  let expectedResult: INroSerieTitulo | INroSerieTitulo[] | boolean | null;
  let currentDate: dayjs.Dayjs;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(NroSerieTituloService);
    httpMock = TestBed.inject(HttpTestingController);
    currentDate = dayjs();

    elemDefault = {
      id: 0,
      nroSerieInicio: 0,
      nroSerieFin: 0,
      fechaInicio: currentDate,
      fechaFin: currentDate,
      usuarioAlta: 'AAAAAAA',
      usuarioModificacion: 'AAAAAAA',
      serie: 0,
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign(
        {
          fechaInicio: currentDate.format(DATE_FORMAT),
          fechaFin: currentDate.format(DATE_FORMAT),
        },
        elemDefault
      );

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a NroSerieTitulo', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
          fechaInicio: currentDate.format(DATE_FORMAT),
          fechaFin: currentDate.format(DATE_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          fechaInicio: currentDate,
          fechaFin: currentDate,
        },
        returnedFromService
      );

      service.create(new NroSerieTitulo()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a NroSerieTitulo', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          nroSerieInicio: 1,
          nroSerieFin: 1,
          fechaInicio: currentDate.format(DATE_FORMAT),
          fechaFin: currentDate.format(DATE_FORMAT),
          usuarioAlta: 'BBBBBB',
          usuarioModificacion: 'BBBBBB',
          serie: 1,
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          fechaInicio: currentDate,
          fechaFin: currentDate,
        },
        returnedFromService
      );

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a NroSerieTitulo', () => {
      const patchObject = Object.assign(
        {
          nroSerieFin: 1,
          fechaInicio: currentDate.format(DATE_FORMAT),
        },
        new NroSerieTitulo()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign(
        {
          fechaInicio: currentDate,
          fechaFin: currentDate,
        },
        returnedFromService
      );

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of NroSerieTitulo', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          nroSerieInicio: 1,
          nroSerieFin: 1,
          fechaInicio: currentDate.format(DATE_FORMAT),
          fechaFin: currentDate.format(DATE_FORMAT),
          usuarioAlta: 'BBBBBB',
          usuarioModificacion: 'BBBBBB',
          serie: 1,
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          fechaInicio: currentDate,
          fechaFin: currentDate,
        },
        returnedFromService
      );

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a NroSerieTitulo', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addNroSerieTituloToCollectionIfMissing', () => {
      it('should add a NroSerieTitulo to an empty array', () => {
        const nroSerieTitulo: INroSerieTitulo = { id: 123 };
        expectedResult = service.addNroSerieTituloToCollectionIfMissing([], nroSerieTitulo);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(nroSerieTitulo);
      });

      it('should not add a NroSerieTitulo to an array that contains it', () => {
        const nroSerieTitulo: INroSerieTitulo = { id: 123 };
        const nroSerieTituloCollection: INroSerieTitulo[] = [
          {
            ...nroSerieTitulo,
          },
          { id: 456 },
        ];
        expectedResult = service.addNroSerieTituloToCollectionIfMissing(nroSerieTituloCollection, nroSerieTitulo);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a NroSerieTitulo to an array that doesn't contain it", () => {
        const nroSerieTitulo: INroSerieTitulo = { id: 123 };
        const nroSerieTituloCollection: INroSerieTitulo[] = [{ id: 456 }];
        expectedResult = service.addNroSerieTituloToCollectionIfMissing(nroSerieTituloCollection, nroSerieTitulo);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(nroSerieTitulo);
      });

      it('should add only unique NroSerieTitulo to an array', () => {
        const nroSerieTituloArray: INroSerieTitulo[] = [{ id: 123 }, { id: 456 }, { id: 22909 }];
        const nroSerieTituloCollection: INroSerieTitulo[] = [{ id: 123 }];
        expectedResult = service.addNroSerieTituloToCollectionIfMissing(nroSerieTituloCollection, ...nroSerieTituloArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const nroSerieTitulo: INroSerieTitulo = { id: 123 };
        const nroSerieTitulo2: INroSerieTitulo = { id: 456 };
        expectedResult = service.addNroSerieTituloToCollectionIfMissing([], nroSerieTitulo, nroSerieTitulo2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(nroSerieTitulo);
        expect(expectedResult).toContain(nroSerieTitulo2);
      });

      it('should accept null and undefined values', () => {
        const nroSerieTitulo: INroSerieTitulo = { id: 123 };
        expectedResult = service.addNroSerieTituloToCollectionIfMissing([], null, nroSerieTitulo, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(nroSerieTitulo);
      });

      it('should return initial array if no NroSerieTitulo is added', () => {
        const nroSerieTituloCollection: INroSerieTitulo[] = [{ id: 123 }];
        expectedResult = service.addNroSerieTituloToCollectionIfMissing(nroSerieTituloCollection, undefined, null);
        expect(expectedResult).toEqual(nroSerieTituloCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
