import dayjs from 'dayjs/esm';
import { IAlumnoAnalitico } from 'app/entities/alumno-analitico/alumno-analitico.model';
import { IAlumnoTitulo } from 'app/entities/alumno-titulo/alumno-titulo.model';
import { IAlumno } from 'app/entities/alumno/alumno.model';
import { IOfertaEducativa } from 'app/entities/oferta-educativa/oferta-educativa.model';

export interface IAlumnoEstabOferta {
  id?: number;
  idSer?: number | null;
  idOfertaEducativa?: number | null;
  idAlumno?: number | null;
  idEstadoAlumnoEstabOferta?: number | null;
  fechaInicio?: dayjs.Dayjs | null;
  fechaFin?: dayjs.Dayjs | null;
  alumnoAnaliticos?: IAlumnoAnalitico[] | null;
  alumnoTitulos?: IAlumnoTitulo[] | null;
  alumno?: IAlumno | null;
  ofertaEducativa?: IOfertaEducativa | null;
}

export class AlumnoEstabOferta implements IAlumnoEstabOferta {
  constructor(
    public id?: number,
    public idSer?: number | null,
    public idOfertaEducativa?: number | null,
    public idAlumno?: number | null,
    public idEstadoAlumnoEstabOferta?: number | null,
    public fechaInicio?: dayjs.Dayjs | null,
    public fechaFin?: dayjs.Dayjs | null,
    public alumnoAnaliticos?: IAlumnoAnalitico[] | null,
    public alumnoTitulos?: IAlumnoTitulo[] | null,
    public alumno?: IAlumno | null,
    public ofertaEducativa?: IOfertaEducativa | null
  ) {}
}

export function getAlumnoEstabOfertaIdentifier(alumnoEstabOferta: IAlumnoEstabOferta): number | undefined {
  return alumnoEstabOferta.id;
}
