import { IAlumnoEstabOferta } from 'app/entities/alumno-estab-oferta/alumno-estab-oferta.model';
import { IMateria } from 'app/entities/materia/materia.model';

export interface IAlumnoAnalitico {
  id?: number;
  nota?: number | null;
  idEstadoAlumnoAnalitico?: number | null;
  idAlumnoEstabOferta?: number | null;
  idMateria?: number | null;
  idMesImp?: number | null;
  idAnoImp?: number | null;
  establecimientoImp?: string | null;
  alumnoEstabOferta?: IAlumnoEstabOferta | null;
  materia?: IMateria | null;
}

export class AlumnoAnalitico implements IAlumnoAnalitico {
  constructor(
    public id?: number,
    public nota?: number | null,
    public idEstadoAlumnoAnalitico?: number | null,
    public idAlumnoEstabOferta?: number | null,
    public idMateria?: number | null,
    public idMesImp?: number | null,
    public idAnoImp?: number | null,
    public establecimientoImp?: string | null,
    public alumnoEstabOferta?: IAlumnoEstabOferta | null,
    public materia?: IMateria | null
  ) {}
}

export function getAlumnoAnaliticoIdentifier(alumnoAnalitico: IAlumnoAnalitico): number | undefined {
  return alumnoAnalitico.id;
}
