import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IJornada } from '../jornada.model';

import { JornadaService } from './jornada.service';

describe('Jornada Service', () => {
  let service: JornadaService;
  let httpMock: HttpTestingController;
  let elemDefault: IJornada;
  let expectedResult: IJornada | IJornada[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(JornadaService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 0,
      codigo: 'AAAAAAA',
      descripcion: 'AAAAAAA',
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign({}, elemDefault);

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should return a list of Jornada', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          codigo: 'BBBBBB',
          descripcion: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    describe('addJornadaToCollectionIfMissing', () => {
      it('should add a Jornada to an empty array', () => {
        const jornada: IJornada = { id: 123 };
        expectedResult = service.addJornadaToCollectionIfMissing([], jornada);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(jornada);
      });

      it('should not add a Jornada to an array that contains it', () => {
        const jornada: IJornada = { id: 123 };
        const jornadaCollection: IJornada[] = [
          {
            ...jornada,
          },
          { id: 456 },
        ];
        expectedResult = service.addJornadaToCollectionIfMissing(jornadaCollection, jornada);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Jornada to an array that doesn't contain it", () => {
        const jornada: IJornada = { id: 123 };
        const jornadaCollection: IJornada[] = [{ id: 456 }];
        expectedResult = service.addJornadaToCollectionIfMissing(jornadaCollection, jornada);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(jornada);
      });

      it('should add only unique Jornada to an array', () => {
        const jornadaArray: IJornada[] = [{ id: 123 }, { id: 456 }, { id: 19511 }];
        const jornadaCollection: IJornada[] = [{ id: 123 }];
        expectedResult = service.addJornadaToCollectionIfMissing(jornadaCollection, ...jornadaArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const jornada: IJornada = { id: 123 };
        const jornada2: IJornada = { id: 456 };
        expectedResult = service.addJornadaToCollectionIfMissing([], jornada, jornada2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(jornada);
        expect(expectedResult).toContain(jornada2);
      });

      it('should accept null and undefined values', () => {
        const jornada: IJornada = { id: 123 };
        expectedResult = service.addJornadaToCollectionIfMissing([], null, jornada, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(jornada);
      });

      it('should return initial array if no Jornada is added', () => {
        const jornadaCollection: IJornada[] = [{ id: 123 }];
        expectedResult = service.addJornadaToCollectionIfMissing(jornadaCollection, undefined, null);
        expect(expectedResult).toEqual(jornadaCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
