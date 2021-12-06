import { Tema } from "./Tema";
import { UserLogin } from "./UserLogin";

export class Postagem{
    public idPost: number;
    public titulo: string;
    public texto: string;
    public data: Date;

    public usuario: UserLogin
    public tema: Tema
}