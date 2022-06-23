import dayjs from 'dayjs/esm';
import { IAlumnoEstabOferta } from 'app/entities/alumno-estab-oferta/alumno-estab-oferta.model';

export interface IOfertaEducativa {
  id?: number;
  idModalidad?: number | null;
  idNivel?: number | null;
  idOrientacion?: number | null;
  idTituloDenominacion?: number | null;
  observiaciones?: string | null;
  idTipoTitulo?: number | null;
  idUser?: number | null;
  fechaInicio?: dayjs.Dayjs | null;
  fechaFin?: dayjs.Dayjs | null;
  fechaEstado?: dayjs.Dayjs | null;
  idEstadoOferta?: number | null;
  idLeyEducacion?: number | null;
  idNormaAprobacionDen?: number | null;
  idNormaDictamenDen?: number | null;
  idSeCorrespondeCon?: number | null;
  titulo?: string | null;
  tituloImpresion?: string | null;
  tituloIntermedio?: string | null;
  tituloIntermedioImpresion?: string | null;
  orientacion?: string | null;
  idRama?: number | null;
  idSeccionTituloIntermedio?: number | null;
  idCursoGrupoNombre?: number | null;
  correlatividad?: number | null;
  idModalidadDictado?: number | null;
  titula?: number | null;
  cicloBasico?: number | null;
  alumnoEstabOfertas?: IAlumnoEstabOferta[] | null;
}

export class OfertaEducativa implements IOfertaEducativa {
  constructor(
    public id?: number,
    public idModalidad?: number | null,
    public idNivel?: number | null,
    public idOrientacion?: number | null,
    public idTituloDenominacion?: number | null,
    public observiaciones?: string | null,
    public idTipoTitulo?: number | null,
    public idUser?: number | null,
    public fechaInicio?: dayjs.Dayjs | null,
    public fechaFin?: dayjs.Dayjs | null,
    public fechaEstado?: dayjs.Dayjs | null,
    public idEstadoOferta?: number | null,
    public idLeyEducacion?: number | null,
    public idNormaAprobacionDen?: number | null,
    public idNormaDictamenDen?: number | null,
    public idSeCorrespondeCon?: number | null,
    public titulo?: string | null,
    public tituloImpresion?: string | null,
    public tituloIntermedio?: string | null,
    public tituloIntermedioImpresion?: string | null,
    public orientacion?: string | null,
    public idRama?: number | null,
    public idSeccionTituloIntermedio?: number | null,
    public idCursoGrupoNombre?: number | null,
    public correlatividad?: number | null,
    public idModalidadDictado?: number | null,
    public titula?: number | null,
    public cicloBasico?: number | null,
    public alumnoEstabOfertas?: IAlumnoEstabOferta[] | null
  ) {}
}

export function getOfertaEducativaIdentifier(ofertaEducativa: IOfertaEducativa): number | undefined {
  return ofertaEducativa.id;
}
