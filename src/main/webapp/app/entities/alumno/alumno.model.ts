import dayjs from 'dayjs/esm';
import { IAlumnoEstabOferta } from 'app/entities/alumno-estab-oferta/alumno-estab-oferta.model';

export interface IAlumno {
  id?: number;
  documento?: string | null;
  idTipoDocumento?: number | null;
  nombre?: string | null;
  apellido?: string | null;
  fechaNacimento?: dayjs.Dayjs | null;
  sexo?: string | null;
  ciudadNacimiento?: string | null;
  provinciaNacimiento?: string | null;
  idNacionalidad?: number | null;
  idSerCreador?: number | null;
  idProvincia?: number | null;
  alumnoEstabOfertas?: IAlumnoEstabOferta[] | null;
}

export class Alumno implements IAlumno {
  constructor(
    public id?: number,
    public documento?: string | null,
    public idTipoDocumento?: number | null,
    public nombre?: string | null,
    public apellido?: string | null,
    public fechaNacimento?: dayjs.Dayjs | null,
    public sexo?: string | null,
    public ciudadNacimiento?: string | null,
    public provinciaNacimiento?: string | null,
    public idNacionalidad?: number | null,
    public idSerCreador?: number | null,
    public idProvincia?: number | null,
    public alumnoEstabOfertas?: IAlumnoEstabOferta[] | null
  ) {}
}

export function getAlumnoIdentifier(alumno: IAlumno): number | undefined {
  return alumno.id;
}
