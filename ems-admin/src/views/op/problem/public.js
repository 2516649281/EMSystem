var oldProblem = {};

export function setProblem(data) {
  oldProblem = data;
}

export function getProblem() {
  return oldProblem;
}
