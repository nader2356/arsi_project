export interface Event {
  id?: number;
  title: string;
  description: string;
  date: Date;
  formateur: string;
  image: any;
  maxOfParticipants: number;
  numberOfParticipants: number;
  location: string;
  status: boolean;
  price: number;
  type: string;
  }