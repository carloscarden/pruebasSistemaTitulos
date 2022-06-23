export interface IJornada {
  id?: number;
  codigo?: string | null;
  descripcion?: string | null;
}

export class Jornada implements IJornada {
  constructor(public id?: number, public codigo?: string | null, public descripcion?: string | null) {}
}

export function getJornadaIdentifier(jornada: IJornada): number | undefined {
  return jornada.id;
}
