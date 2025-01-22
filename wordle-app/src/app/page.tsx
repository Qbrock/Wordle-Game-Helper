import Image from "next/image";

export default function Home() {
  return (
    <div>
      <h1 className="text-3xl">Wordle</h1>
      <Image
        src="/Wordle_logo.png"
        alt="Wordle"
        width={500}
        height={500}
      />
    </div>
  );
}
