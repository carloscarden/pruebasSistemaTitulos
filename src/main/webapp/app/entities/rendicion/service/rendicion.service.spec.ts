import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import dayjs from 'dayjs/esm';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IRendicion, Rendicion } from '../rendicion.model';

import { RendicionService } from './rendicion.service';

describe('Rendicion Service', () => {
  let service: RendicionService;
  let httpMock: HttpTestingController;
  let elemDefault: IRendicion;
  let expectedResult: IRendicion | IRendicion[] | boolean | null;
  let currentDate: dayjs.Dayjs;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(RendicionService);
    httpMock = TestBed.inject(HttpTestingController);
    currentDate = dayjs();

    elemDefault = {
      id: 0,
      dniUsuario: 'AAAAAAA',
      nombreUsuario: 'AAAAAAA',
      dniUsuarioAnulador: 'AAAAAAA',
      nombreUsuarioAnulador: 'AAAAAAA',
      motivo: 'AAAAAAA',
      causaRechazo: 'AAAAAAA',
      fechaAnulacion: currentDate,
      dniAlumno: 'AAAAAAA',
      nombreAlumno: 'AAAAAAA',
      alumnoTituloId: 0,
      establecimientoId: 0,
      claveEstab: 'AAAAAAA',
      rama: 'AAAAAAA',
      nroFormulario: 0,
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign(
        {
          fechaAnulacion: currentDate.format(DATE_FORMAT),
        },
        elemDefault
      );

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a Rendicion', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
          fechaAnulacion: currentDate.format(DATE_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          fechaAnulacion: currentDate,
        },
        returnedFromService
      );

      service.create(new Rendicion()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Rendicion', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          dniUsuario: 'BBBBBB',
          nombreUsuario: 'BBBBBB',
          dniUsuarioAnulador: 'BBBBBB',
          nombreUsuarioAnulador: 'BBBBBB',
          motivo: 'BBBBBB',
          causaRechazo: 'BBBBBB',
          fechaAnulacion: currentDate.format(DATE_FORMAT),
          dniAlumno: 'BBBBBB',
          nombreAlumno: 'BBBBBB',
          alumnoTituloId: 1,
          establecimientoId: 1,
          claveEstab: 'BBBBBB',
          rama: 'BBBBBB',
          nroFormulario: 1,
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          fechaAnulacion: currentDate,
        },
        returnedFromService
      );

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Rendicion', () => {
      const patchObject = Object.assign(
        {
          dniUsuario: 'BBBBBB',
          nombreUsuario: 'BBBBBB',
          dniUsuarioAnulador: 'BBBBBB',
          nombreUsuarioAnulador: 'BBBBBB',
          fechaAnulacion: currentDate.format(DATE_FORMAT),
          nombreAlumno: 'BBBBBB',
          establecimientoId: 1,
          claveEstab: 'BBBBBB',
          rama: 'BBBBBB',
          nroFormulario: 1,
        },
        new Rendicion()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign(
        {
          fechaAnulacion: currentDate,
        },
        returnedFromService
      );

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Rendicion', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          dniUsuario: 'BBBBBB',
          nombreUsuario: 'BBBBBB',
          dniUsuarioAnulador: 'BBBBBB',
          nombreUsuarioAnulador: 'BBBBBB',
          motivo: 'BBBBBB',
          causaRechazo: 'BBBBBB',
          fechaAnulacion: currentDate.format(DATE_FORMAT),
          dniAlumno: 'BBBBBB',
          nombreAlumno: 'BBBBBB',
          alumnoTituloId: 1,
          establecimientoId: 1,
          claveEstab: 'BBBBBB',
          rama: 'BBBBBB',
          nroFormulario: 1,
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          fechaAnulacion: currentDate,
        },
        returnedFromService
      );

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a Rendicion', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addRendicionToCollectionIfMissing', () => {
      it('should add a Rendicion to an empty array', () => {
        const rendicion: IRendicion = { id: 123 };
        expectedResult = service.addRendicionToCollectionIfMissing([], rendicion);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(rendicion);
      });

      it('should not add a Rendicion to an array that contains it', () => {
        const rendicion: IRendicion = { id: 123 };
        const rendicionCollection: IRendicion[] = [
          {
            ...rendicion,
          },
          { id: 456 },
        ];
        expectedResult = service.addRendicionToCollectionIfMissing(rendicionCollection, rendicion);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Rendicion to an array that doesn't contain it", () => {
        const rendicion: IRendicion = { id: 123 };
        const rendicionCollection: IRendicion[] = [{ id: 456 }];
        expectedResult = service.addRendicionToCollectionIfMissing(rendicionCollection, rendicion);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(rendicion);
      });

      it('should add only unique Rendicion to an array', () => {
        const rendicionArray: IRendicion[] = [{ id: 123 }, { id: 456 }, { id: 17044 }];
        const rendicionCollection: IRendicion[] = [{ id: 123 }];
        expectedResult = service.addRendicionToCollectionIfMissing(rendicionCollection, ...rendicionArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const rendicion: IRendicion = { id: 123 };
        const rendicion2: IRendicion = { id: 456 };
        expectedResult = service.addRendicionToCollectionIfMissing([], rendicion, rendicion2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(rendicion);
        expect(expectedResult).toContain(rendicion2);
      });

      it('should accept null and undefined values', () => {
        const rendicion: IRendicion = { id: 123 };
        expectedResult = service.addRendicionToCollectionIfMissing([], null, rendicion, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(rendicion);
      });

      it('should return initial array if no Rendicion is added', () => {
        const rendicionCollection: IRendicion[] = [{ id: 123 }];
        expectedResult = service.addRendicionToCollectionIfMissing(rendicionCollection, undefined, null);
        expect(expectedResult).toEqual(rendicionCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
