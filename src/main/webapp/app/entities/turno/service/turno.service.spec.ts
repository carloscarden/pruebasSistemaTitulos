import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import dayjs from 'dayjs/esm';

import { DATE_FORMAT } from 'app/config/input.constants';
import { ITurno } from '../turno.model';

import { TurnoService } from './turno.service';

describe('Turno Service', () => {
  let service: TurnoService;
  let httpMock: HttpTestingController;
  let elemDefault: ITurno;
  let expectedResult: ITurno | ITurno[] | boolean | null;
  let currentDate: dayjs.Dayjs;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(TurnoService);
    httpMock = TestBed.inject(HttpTestingController);
    currentDate = dayjs();

    elemDefault = {
      id: 0,
      turno: 'AAAAAAA',
      descripcion: currentDate,
      vigh: currentDate,
      orden: 0,
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign(
        {
          descripcion: currentDate.format(DATE_FORMAT),
          vigh: currentDate.format(DATE_FORMAT),
        },
        elemDefault
      );

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should return a list of Turno', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          turno: 'BBBBBB',
          descripcion: currentDate.format(DATE_FORMAT),
          vigh: currentDate.format(DATE_FORMAT),
          orden: 1,
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          descripcion: currentDate,
          vigh: currentDate,
        },
        returnedFromService
      );

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    describe('addTurnoToCollectionIfMissing', () => {
      it('should add a Turno to an empty array', () => {
        const turno: ITurno = { id: 123 };
        expectedResult = service.addTurnoToCollectionIfMissing([], turno);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(turno);
      });

      it('should not add a Turno to an array that contains it', () => {
        const turno: ITurno = { id: 123 };
        const turnoCollection: ITurno[] = [
          {
            ...turno,
          },
          { id: 456 },
        ];
        expectedResult = service.addTurnoToCollectionIfMissing(turnoCollection, turno);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Turno to an array that doesn't contain it", () => {
        const turno: ITurno = { id: 123 };
        const turnoCollection: ITurno[] = [{ id: 456 }];
        expectedResult = service.addTurnoToCollectionIfMissing(turnoCollection, turno);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(turno);
      });

      it('should add only unique Turno to an array', () => {
        const turnoArray: ITurno[] = [{ id: 123 }, { id: 456 }, { id: 27697 }];
        const turnoCollection: ITurno[] = [{ id: 123 }];
        expectedResult = service.addTurnoToCollectionIfMissing(turnoCollection, ...turnoArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const turno: ITurno = { id: 123 };
        const turno2: ITurno = { id: 456 };
        expectedResult = service.addTurnoToCollectionIfMissing([], turno, turno2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(turno);
        expect(expectedResult).toContain(turno2);
      });

      it('should accept null and undefined values', () => {
        const turno: ITurno = { id: 123 };
        expectedResult = service.addTurnoToCollectionIfMissing([], null, turno, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(turno);
      });

      it('should return initial array if no Turno is added', () => {
        const turnoCollection: ITurno[] = [{ id: 123 }];
        expectedResult = service.addTurnoToCollectionIfMissing(turnoCollection, undefined, null);
        expect(expectedResult).toEqual(turnoCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
