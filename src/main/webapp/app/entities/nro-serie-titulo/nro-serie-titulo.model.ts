import dayjs from 'dayjs/esm';

export interface INroSerieTitulo {
  id?: number;
  nroSerieInicio?: number | null;
  nroSerieFin?: number | null;
  fechaInicio?: dayjs.Dayjs | null;
  fechaFin?: dayjs.Dayjs | null;
  usuarioAlta?: string | null;
  usuarioModificacion?: string | null;
  serie?: number | null;
}

export class NroSerieTitulo implements INroSerieTitulo {
  constructor(
    public id?: number,
    public nroSerieInicio?: number | null,
    public nroSerieFin?: number | null,
    public fechaInicio?: dayjs.Dayjs | null,
    public fechaFin?: dayjs.Dayjs | null,
    public usuarioAlta?: string | null,
    public usuarioModificacion?: string | null,
    public serie?: number | null
  ) {}
}

export function getNroSerieTituloIdentifier(nroSerieTitulo: INroSerieTitulo): number | undefined {
  return nroSerieTitulo.id;
}
