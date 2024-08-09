interface UserState {
  refresh: string | undefined;
  username: string | undefined;
  email: string | undefined;
  accessToken?: string | undefined;
}

interface AuthState {
  isLoading: boolean;
  isAuthenticated: boolean;
  user?: null | UserState;
}

interface LoginFormData {
  username: string;
  password: string;
}

interface SignupFormData {
  username: string;
  email: string;
  password: string;
}

export type { UserState, AuthState, LoginFormData, SignupFormData };
