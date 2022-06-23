import dayjs from 'dayjs/esm';

export interface ITurno {
  id?: number;
  turno?: string | null;
  descripcion?: dayjs.Dayjs | null;
  vigh?: dayjs.Dayjs | null;
  orden?: number | null;
}

export class Turno implements ITurno {
  constructor(
    public id?: number,
    public turno?: string | null,
    public descripcion?: dayjs.Dayjs | null,
    public vigh?: dayjs.Dayjs | null,
    public orden?: number | null
  ) {}
}

export function getTurnoIdentifier(turno: ITurno): number | undefined {
  return turno.id;
}
