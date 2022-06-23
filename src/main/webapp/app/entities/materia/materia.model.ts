import { IAlumnoAnalitico } from 'app/entities/alumno-analitico/alumno-analitico.model';

export interface IMateria {
  id?: number;
  idMateriaDenominacion?: number | null;
  marcaModulo?: string | null;
  cargaHoraria?: number | null;
  idOfertaEducativa?: number | null;
  idSeccion?: number | null;
  idTipoMateriaOpcional?: number | null;
  orden?: number | null;
  idArea?: number | null;
  idAsignatira?: number | null;
  codigosChequeados?: number | null;
  obligatoriedad?: number | null;
  unidadCargaPedagogica?: number | null;
  alumnoAnaliticos?: IAlumnoAnalitico[] | null;
}

export class Materia implements IMateria {
  constructor(
    public id?: number,
    public idMateriaDenominacion?: number | null,
    public marcaModulo?: string | null,
    public cargaHoraria?: number | null,
    public idOfertaEducativa?: number | null,
    public idSeccion?: number | null,
    public idTipoMateriaOpcional?: number | null,
    public orden?: number | null,
    public idArea?: number | null,
    public idAsignatira?: number | null,
    public codigosChequeados?: number | null,
    public obligatoriedad?: number | null,
    public unidadCargaPedagogica?: number | null,
    public alumnoAnaliticos?: IAlumnoAnalitico[] | null
  ) {}
}

export function getMateriaIdentifier(materia: IMateria): number | undefined {
  return materia.id;
}
