import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import dayjs from 'dayjs/esm';

import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IAlumnoTitulo, AlumnoTitulo } from '../alumno-titulo.model';

import { AlumnoTituloService } from './alumno-titulo.service';

describe('AlumnoTitulo Service', () => {
  let service: AlumnoTituloService;
  let httpMock: HttpTestingController;
  let elemDefault: IAlumnoTitulo;
  let expectedResult: IAlumnoTitulo | IAlumnoTitulo[] | boolean | null;
  let currentDate: dayjs.Dayjs;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(AlumnoTituloService);
    httpMock = TestBed.inject(HttpTestingController);
    currentDate = dayjs();

    elemDefault = {
      id: 0,
      idAlumnoEstabOferta: 0,
      fechaEmision: currentDate,
      nroSerie: 'AAAAAAA',
      numeroRfifd: 'AAAAAAA',
      validezNacional: 'AAAAAAA',
      fechaEnvio: currentDate,
      fechaEgreso: currentDate,
      nroLibroMatriz: 0,
      nroActa: 0,
      nroFolio: 0,
      estabNombre: 'AAAAAAA',
      estabCue: 'AAAAAAA',
      estabUbicadoEn: 'AAAAAAA',
      estabCiudad: 'AAAAAAA',
      estabProvincia: 'AAAAAAA',
      idEstadoAlumnoTitulo: 0,
      tituloIntermedio: 0,
      promedio: 'AAAAAAA',
      fechaRuta: currentDate,
      idRamaRuta: 0,
      apYnomListoParaEnviar: 'AAAAAAA',
      documentoListoParaEnviar: 'AAAAAAA',
      apYnomEnviado: 'AAAAAAA',
      documentoEnviado: 'AAAAAAA',
      fechaUltimoEstado: currentDate,
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign(
        {
          fechaEmision: currentDate.format(DATE_TIME_FORMAT),
          fechaEnvio: currentDate.format(DATE_TIME_FORMAT),
          fechaEgreso: currentDate.format(DATE_TIME_FORMAT),
          fechaRuta: currentDate.format(DATE_TIME_FORMAT),
          fechaUltimoEstado: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a AlumnoTitulo', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
          fechaEmision: currentDate.format(DATE_TIME_FORMAT),
          fechaEnvio: currentDate.format(DATE_TIME_FORMAT),
          fechaEgreso: currentDate.format(DATE_TIME_FORMAT),
          fechaRuta: currentDate.format(DATE_TIME_FORMAT),
          fechaUltimoEstado: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          fechaEmision: currentDate,
          fechaEnvio: currentDate,
          fechaEgreso: currentDate,
          fechaRuta: currentDate,
          fechaUltimoEstado: currentDate,
        },
        returnedFromService
      );

      service.create(new AlumnoTitulo()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a AlumnoTitulo', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          idAlumnoEstabOferta: 1,
          fechaEmision: currentDate.format(DATE_TIME_FORMAT),
          nroSerie: 'BBBBBB',
          numeroRfifd: 'BBBBBB',
          validezNacional: 'BBBBBB',
          fechaEnvio: currentDate.format(DATE_TIME_FORMAT),
          fechaEgreso: currentDate.format(DATE_TIME_FORMAT),
          nroLibroMatriz: 1,
          nroActa: 1,
          nroFolio: 1,
          estabNombre: 'BBBBBB',
          estabCue: 'BBBBBB',
          estabUbicadoEn: 'BBBBBB',
          estabCiudad: 'BBBBBB',
          estabProvincia: 'BBBBBB',
          idEstadoAlumnoTitulo: 1,
          tituloIntermedio: 1,
          promedio: 'BBBBBB',
          fechaRuta: currentDate.format(DATE_TIME_FORMAT),
          idRamaRuta: 1,
          apYnomListoParaEnviar: 'BBBBBB',
          documentoListoParaEnviar: 'BBBBBB',
          apYnomEnviado: 'BBBBBB',
          documentoEnviado: 'BBBBBB',
          fechaUltimoEstado: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          fechaEmision: currentDate,
          fechaEnvio: currentDate,
          fechaEgreso: currentDate,
          fechaRuta: currentDate,
          fechaUltimoEstado: currentDate,
        },
        returnedFromService
      );

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a AlumnoTitulo', () => {
      const patchObject = Object.assign(
        {
          numeroRfifd: 'BBBBBB',
          validezNacional: 'BBBBBB',
          fechaEnvio: currentDate.format(DATE_TIME_FORMAT),
          fechaEgreso: currentDate.format(DATE_TIME_FORMAT),
          nroLibroMatriz: 1,
          nroActa: 1,
          nroFolio: 1,
          estabUbicadoEn: 'BBBBBB',
          estabCiudad: 'BBBBBB',
          estabProvincia: 'BBBBBB',
          idEstadoAlumnoTitulo: 1,
          tituloIntermedio: 1,
          promedio: 'BBBBBB',
          fechaRuta: currentDate.format(DATE_TIME_FORMAT),
          apYnomListoParaEnviar: 'BBBBBB',
          documentoListoParaEnviar: 'BBBBBB',
          apYnomEnviado: 'BBBBBB',
          fechaUltimoEstado: currentDate.format(DATE_TIME_FORMAT),
        },
        new AlumnoTitulo()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign(
        {
          fechaEmision: currentDate,
          fechaEnvio: currentDate,
          fechaEgreso: currentDate,
          fechaRuta: currentDate,
          fechaUltimoEstado: currentDate,
        },
        returnedFromService
      );

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of AlumnoTitulo', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          idAlumnoEstabOferta: 1,
          fechaEmision: currentDate.format(DATE_TIME_FORMAT),
          nroSerie: 'BBBBBB',
          numeroRfifd: 'BBBBBB',
          validezNacional: 'BBBBBB',
          fechaEnvio: currentDate.format(DATE_TIME_FORMAT),
          fechaEgreso: currentDate.format(DATE_TIME_FORMAT),
          nroLibroMatriz: 1,
          nroActa: 1,
          nroFolio: 1,
          estabNombre: 'BBBBBB',
          estabCue: 'BBBBBB',
          estabUbicadoEn: 'BBBBBB',
          estabCiudad: 'BBBBBB',
          estabProvincia: 'BBBBBB',
          idEstadoAlumnoTitulo: 1,
          tituloIntermedio: 1,
          promedio: 'BBBBBB',
          fechaRuta: currentDate.format(DATE_TIME_FORMAT),
          idRamaRuta: 1,
          apYnomListoParaEnviar: 'BBBBBB',
          documentoListoParaEnviar: 'BBBBBB',
          apYnomEnviado: 'BBBBBB',
          documentoEnviado: 'BBBBBB',
          fechaUltimoEstado: currentDate.format(DATE_TIME_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          fechaEmision: currentDate,
          fechaEnvio: currentDate,
          fechaEgreso: currentDate,
          fechaRuta: currentDate,
          fechaUltimoEstado: currentDate,
        },
        returnedFromService
      );

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a AlumnoTitulo', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addAlumnoTituloToCollectionIfMissing', () => {
      it('should add a AlumnoTitulo to an empty array', () => {
        const alumnoTitulo: IAlumnoTitulo = { id: 123 };
        expectedResult = service.addAlumnoTituloToCollectionIfMissing([], alumnoTitulo);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(alumnoTitulo);
      });

      it('should not add a AlumnoTitulo to an array that contains it', () => {
        const alumnoTitulo: IAlumnoTitulo = { id: 123 };
        const alumnoTituloCollection: IAlumnoTitulo[] = [
          {
            ...alumnoTitulo,
          },
          { id: 456 },
        ];
        expectedResult = service.addAlumnoTituloToCollectionIfMissing(alumnoTituloCollection, alumnoTitulo);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a AlumnoTitulo to an array that doesn't contain it", () => {
        const alumnoTitulo: IAlumnoTitulo = { id: 123 };
        const alumnoTituloCollection: IAlumnoTitulo[] = [{ id: 456 }];
        expectedResult = service.addAlumnoTituloToCollectionIfMissing(alumnoTituloCollection, alumnoTitulo);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(alumnoTitulo);
      });

      it('should add only unique AlumnoTitulo to an array', () => {
        const alumnoTituloArray: IAlumnoTitulo[] = [{ id: 123 }, { id: 456 }, { id: 11801 }];
        const alumnoTituloCollection: IAlumnoTitulo[] = [{ id: 123 }];
        expectedResult = service.addAlumnoTituloToCollectionIfMissing(alumnoTituloCollection, ...alumnoTituloArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const alumnoTitulo: IAlumnoTitulo = { id: 123 };
        const alumnoTitulo2: IAlumnoTitulo = { id: 456 };
        expectedResult = service.addAlumnoTituloToCollectionIfMissing([], alumnoTitulo, alumnoTitulo2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(alumnoTitulo);
        expect(expectedResult).toContain(alumnoTitulo2);
      });

      it('should accept null and undefined values', () => {
        const alumnoTitulo: IAlumnoTitulo = { id: 123 };
        expectedResult = service.addAlumnoTituloToCollectionIfMissing([], null, alumnoTitulo, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(alumnoTitulo);
      });

      it('should return initial array if no AlumnoTitulo is added', () => {
        const alumnoTituloCollection: IAlumnoTitulo[] = [{ id: 123 }];
        expectedResult = service.addAlumnoTituloToCollectionIfMissing(alumnoTituloCollection, undefined, null);
        expect(expectedResult).toEqual(alumnoTituloCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
