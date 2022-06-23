import dayjs from 'dayjs/esm';
import { IAlumnoEstabOferta } from 'app/entities/alumno-estab-oferta/alumno-estab-oferta.model';

export interface IAlumnoTitulo {
  id?: number;
  idAlumnoEstabOferta?: number | null;
  fechaEmision?: dayjs.Dayjs | null;
  nroSerie?: string | null;
  numeroRfifd?: string | null;
  validezNacional?: string | null;
  fechaEnvio?: dayjs.Dayjs | null;
  fechaEgreso?: dayjs.Dayjs | null;
  nroLibroMatriz?: number | null;
  nroActa?: number | null;
  nroFolio?: number | null;
  estabNombre?: string | null;
  estabCue?: string | null;
  estabUbicadoEn?: string | null;
  estabCiudad?: string | null;
  estabProvincia?: string | null;
  idEstadoAlumnoTitulo?: number | null;
  tituloIntermedio?: number | null;
  promedio?: string | null;
  fechaRuta?: dayjs.Dayjs | null;
  idRamaRuta?: number | null;
  apYnomListoParaEnviar?: string | null;
  documentoListoParaEnviar?: string | null;
  apYnomEnviado?: string | null;
  documentoEnviado?: string | null;
  fechaUltimoEstado?: dayjs.Dayjs | null;
  alumnoEstabOferta?: IAlumnoEstabOferta | null;
}

export class AlumnoTitulo implements IAlumnoTitulo {
  constructor(
    public id?: number,
    public idAlumnoEstabOferta?: number | null,
    public fechaEmision?: dayjs.Dayjs | null,
    public nroSerie?: string | null,
    public numeroRfifd?: string | null,
    public validezNacional?: string | null,
    public fechaEnvio?: dayjs.Dayjs | null,
    public fechaEgreso?: dayjs.Dayjs | null,
    public nroLibroMatriz?: number | null,
    public nroActa?: number | null,
    public nroFolio?: number | null,
    public estabNombre?: string | null,
    public estabCue?: string | null,
    public estabUbicadoEn?: string | null,
    public estabCiudad?: string | null,
    public estabProvincia?: string | null,
    public idEstadoAlumnoTitulo?: number | null,
    public tituloIntermedio?: number | null,
    public promedio?: string | null,
    public fechaRuta?: dayjs.Dayjs | null,
    public idRamaRuta?: number | null,
    public apYnomListoParaEnviar?: string | null,
    public documentoListoParaEnviar?: string | null,
    public apYnomEnviado?: string | null,
    public documentoEnviado?: string | null,
    public fechaUltimoEstado?: dayjs.Dayjs | null,
    public alumnoEstabOferta?: IAlumnoEstabOferta | null
  ) {}
}

export function getAlumnoTituloIdentifier(alumnoTitulo: IAlumnoTitulo): number | undefined {
  return alumnoTitulo.id;
}
