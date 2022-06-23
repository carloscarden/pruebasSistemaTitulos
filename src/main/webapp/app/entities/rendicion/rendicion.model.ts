import dayjs from 'dayjs/esm';

export interface IRendicion {
  id?: number;
  dniUsuario?: string | null;
  nombreUsuario?: string | null;
  dniUsuarioAnulador?: string | null;
  nombreUsuarioAnulador?: string | null;
  motivo?: string | null;
  causaRechazo?: string | null;
  fechaAnulacion?: dayjs.Dayjs | null;
  dniAlumno?: string | null;
  nombreAlumno?: string | null;
  alumnoTituloId?: number | null;
  establecimientoId?: number | null;
  claveEstab?: string | null;
  rama?: string | null;
  nroFormulario?: number | null;
}

export class Rendicion implements IRendicion {
  constructor(
    public id?: number,
    public dniUsuario?: string | null,
    public nombreUsuario?: string | null,
    public dniUsuarioAnulador?: string | null,
    public nombreUsuarioAnulador?: string | null,
    public motivo?: string | null,
    public causaRechazo?: string | null,
    public fechaAnulacion?: dayjs.Dayjs | null,
    public dniAlumno?: string | null,
    public nombreAlumno?: string | null,
    public alumnoTituloId?: number | null,
    public establecimientoId?: number | null,
    public claveEstab?: string | null,
    public rama?: string | null,
    public nroFormulario?: number | null
  ) {}
}

export function getRendicionIdentifier(rendicion: IRendicion): number | undefined {
  return rendicion.id;
}
