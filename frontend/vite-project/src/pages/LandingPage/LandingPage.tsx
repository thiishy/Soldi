import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import authService from '../../services/auth.service';
import './LandingPage.css';

const LandingPage = () => {
  const navigate = useNavigate();

  // Redirecionar para /home se usuário já estiver autenticado
  useEffect(() => {
    if (authService.isAuthenticated()) {
      navigate('/home');
    }
  }, [navigate]);

  // Handler para navegar para registro
  const handleGetStarted = () => {
    navigate('/register');
  };

  // Handler para navegar para login
  const handleLogin = () => {
    navigate('/login');
  };

  return (
    <div className="landing-page">
      {/* HERO SECTION */}
      <section className="hero-section">
        <div className="hero-container">
          <div className="hero-content">
            {/* Logo */}
            <div className="logo">
              <div className="logo-icon">$</div>
              <h1 className="logo-text">Soldi</h1>
            </div>

            {/* Título Principal */}
            <h2 className="hero-title">
              Organize suas finanças de forma inteligente
            </h2>

            {/* Subtítulo */}
            <p className="hero-subtitle">
              Sistema completo de gestão financeira com análise por IA
            </p>

            {/* Botões CTA */}
            <div className="hero-buttons">
              <button
                className="btn btn-primary"
                onClick={handleGetStarted}
                aria-label="Começar agora no Soldi"
              >
                Começar Agora
              </button>
              <button
                className="btn btn-secondary"
                onClick={handleLogin}
                aria-label="Entrar no Soldi"
              >
                Entrar
              </button>
            </div>
          </div>

          {/* Ilustração/Imagem */}
          <div className="hero-image">
            <div className="hero-illustration">
              <svg
                viewBox="0 0 200 200"
                xmlns="http://www.w3.org/2000/svg"
                aria-hidden="true"
              >
                {/* Gráfico de barras ilustrativo */}
                <rect x="20" y="100" width="30" height="80" fill="#99CD85" opacity="0.8" />
                <rect x="60" y="60" width="30" height="120" fill="#99CD85" />
                <rect x="100" y="80" width="30" height="100" fill="#99CD85" opacity="0.9" />
                <rect x="140" y="40" width="30" height="140" fill="#99CD85" opacity="0.7" />

                {/* Linha de tendência */}
                <path
                  d="M 20 120 Q 100 50, 180 80"
                  stroke="#2d3748"
                  strokeWidth="3"
                  fill="none"
                  strokeLinecap="round"
                />

                {/* Pontos na linha */}
                <circle cx="35" cy="110" r="5" fill="#2d3748" />
                <circle cx="75" cy="70" r="5" fill="#2d3748" />
                <circle cx="115" cy="65" r="5" fill="#2d3748" />
                <circle cx="155" cy="85" r="5" fill="#2d3748" />
              </svg>
            </div>
          </div>
        </div>
      </section>

      {/* FEATURES SECTION */}
      <section className="features-section">
        <div className="features-container">
          <h2 className="section-title">Funcionalidades</h2>

          <div className="features-grid">
            {/* Feature 1 */}
            <div className="feature-card">
              <div className="feature-icon">📊</div>
              <h3 className="feature-title">Dashboard Interativo</h3>
              <p className="feature-description">
                Visualize seu saldo e transações em tempo real
              </p>
            </div>

            {/* Feature 2 */}
            <div className="feature-card">
              <div className="feature-icon">💰</div>
              <h3 className="feature-title">Gestão Completa</h3>
              <p className="feature-description">
                Controle receitas e despesas facilmente
              </p>
            </div>

            {/* Feature 3 */}
            <div className="feature-card">
              <div className="feature-icon">🤖</div>
              <h3 className="feature-title">Análise por IA</h3>
              <p className="feature-description">
                Insights personalizados sobre seus gastos
              </p>
            </div>

            {/* Feature 4 */}
            <div className="feature-card">
              <div className="feature-icon">📈</div>
              <h3 className="feature-title">Relatórios Visuais</h3>
              <p className="feature-description">
                Gráficos detalhados por categoria e período
              </p>
            </div>
          </div>
        </div>
      </section>

      {/* CALL TO ACTION FINAL */}
      <section className="cta-section">
        <div className="cta-container">
          <h2 className="cta-title">
            Pronto para transformar sua vida financeira?
          </h2>
          <button
            className="btn btn-primary btn-large"
            onClick={handleGetStarted}
            aria-label="Criar conta grátis no Soldi"
          >
            Criar Conta Grátis
          </button>
        </div>
      </section>

      {/* FOOTER */}
      <footer className="footer">
        <div className="footer-container">
          <div className="footer-content">
            <div className="footer-brand">
              <div className="footer-logo">
                <div className="logo-icon-small">$</div>
                <span className="footer-logo-text">Soldi</span>
              </div>
              <p className="footer-tagline">
                Seu aliado na gestão financeira pessoal
              </p>
            </div>

            <div className="footer-info">
              <p className="footer-text">
                © 2024 Soldi. Sistema de Gestão Financeira.
              </p>
              <p className="footer-text">
                Desenvolvido com ❤️ para facilitar sua vida financeira
              </p>
            </div>
          </div>
        </div>
      </footer>
    </div>
  );
};

export default LandingPage;
