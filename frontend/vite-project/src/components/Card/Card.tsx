import styles from "./Card.module.css";

type CardProps = {
  title: string;
  description: string;
};

export const Card: React.FC<CardProps> = ({ title, description }) => {
  return (
    <div className={styles.card}>
      <div className={styles.content}>
        <h3 className={styles.title}>{title}</h3>
        <p className={styles.description}>{description}</p>
      </div>
    </div>
  );
};
