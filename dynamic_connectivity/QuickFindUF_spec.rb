require_relative 'QuickFindUF'

describe QuickFindUF do
  let(:qfuf) { QuickFindUF.new(10) }

  it 'should connect components' do

    qfuf.union(4,3)
    qfuf.union(3,8)
    qfuf.union(6,5)
    qfuf.union(9,4)
    qfuf.union(2,1)

    qfuf.connected(8,9).should eq (true)
    qfuf.connected(5,0).should eq (false)

    qfuf.union(5,0)
    qfuf.union(7,2)
    qfuf.union(6,1)

    qfuf.id.should eq [1, 1, 1, 8, 8, 1, 1, 1, 8, 8]

  end

end
