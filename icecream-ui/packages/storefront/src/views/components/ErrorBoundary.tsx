import { Component, type ErrorInfo } from 'react'

class ErrorBoundary extends Component {
  constructor(props: any) {
    super(props)
    this.state = {
      hasError: false,
    }
  }
  static getDerivedStateFromError(error: Error) {
    return {
      hasError: true,
      error
    }
  }
  componentDidCatch(error: Error, errorInfo: ErrorInfo) {
    this.setState({ hasError: true, error })
  }

  render() {
    if (this.state.hasError) {
      return (
        <div>
          <h2>Oops, there is an error!</h2>
          <button type="button" onClick={() => this.setState({ hasError: false })}>
            Try again?
          </button>
        </div>
      )
    }
    return this.props.children
  }
}

export default ErrorBoundary
